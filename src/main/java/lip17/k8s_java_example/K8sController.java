package lip17.k8s_java_example;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.models.V1Deployment;
import io.kubernetes.client.openapi.models.V1DeploymentSpec;
import io.kubernetes.client.util.Config;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class K8sController {

    @PutMapping(value = "/scale/{namespace}/{deploymentName}/")
    public ResponseEntity<String> scaleDeployment(
        @PathVariable("namespace") String namespace,
        @PathVariable("deploymentName") String deploymentName,
        @RequestBody ScaleDeploymentRequest request
    ) {
        // path to service account token and CA certificate after deployed as K8S POD
        // /var/run/secrets/kubernetes.io/serviceaccount/{token, ca.crt}
        try {

            // default client will read service account auth info from default location
            // and use K8s generated hostname in ENVAR
            ApiClient apiClient = Config.defaultClient();
            Configuration.setDefaultApiClient(apiClient);

            AppsV1Api api = new AppsV1Api();

            V1Deployment deployment = api.readNamespacedDeployment(deploymentName, namespace).execute();
            V1DeploymentSpec spec = deployment.getSpec();

            // old number of replicas
            int oldReplicas = spec.getReplicas();
            spec.setReplicas(request.replicas);
            api.replaceNamespacedDeployment(deploymentName, namespace, deployment);
            return ResponseEntity.ok("Deployment " + deploymentName + " scaled successfully from " + oldReplicas + " to " +  request.replicas + " replicas in namespace " + namespace);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to scale deployment " + deploymentName);
        }
    }

    private static class ScaleDeploymentRequest {
        private int replicas;

        int getReplicas() {
            return replicas;
        }

        void setReplicas(int replicas) {
            this.replicas = replicas;
        }
    }
}
