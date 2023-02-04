# smart-features-service


## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

## Running the application tests

You can run the application test cases using:
```shell script
./mvnw compile quarkus:test
```

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/smart-features-service-1.0.0-SNAPSHOT-runner`

## Deploy to kubernetes on docker desktop

Install [Kubectl and Docker desktop](https://www.docker.com/products/docker-desktop/) and enable kubernetes extension.
For more information about docker desktop [check documentation](https://docs.docker.com/desktop/)

### Build and deploy the application

Open a terminal to the application root folder.

Build the application using:

```shell script
./mvnw clean package -Dquarkus.kubernetes.deploy=true
```


Then deploy the application using:
```shell script
kubectl apply -f target/kubernetes/kubernetes.yaml
```

Or if you prefer json:
```shell script
kubectl apply -f .target/kubernetes/kubernetes.json
```

And your app will be deployed in a minute, check pods list using:
```shell
kubectl get pod
```