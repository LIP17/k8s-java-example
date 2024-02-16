# k8s-java-example
A demo of how to use K8s Java Client and how to test it

1. Setup local K8s cluster, apply all the resource under `scripts/` 
- namespace.yaml
- service-account.yaml
- cluster-role-binding.yaml
- target-deployment.yaml
- deployment-service.yaml

2. All the Java source code is under `src/`. Use `./gradlew build` to build jar file, this repo requires Java 17+

3. Use local `Dockerfile` to build docker image, and change image and tag accordingly in `deployment-service.yaml`
4. The Java application has default debug port enabled, attach to port 5005 for debugging purpose.