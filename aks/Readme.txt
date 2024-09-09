https://learn.microsoft.com/en-us/azure/aks/workload-identity-deploy-cluster

https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/servicebus/azure-messaging-servicebus/src/samples/java/com/azure/messaging/servicebus/SendMessageWithAzureIdentityAsyncSample.java


https://learn.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-queues?tabs=passwordless


https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/resourcemanager/docs/AUTH.md#simple-authentication

https://github.com/Azure/azure-sdk-for-java/blob/main/sdk/resourcemanager/docs/AUTH.md#preparing-tokencredential

https://github.com/Azure-Samples/service-bus-java-manage-with-claims-based-authorization


cd /c/Work/java-sb-msi/aks/java-app/demo
cd C:\Work\java-sb-msi\aks\java-app\demo
mvn package

java -jar .\target\demo-1.0-SNAPSHOT.jar


java jar ./target/

cd /c/Work/java-sb-msi/aks/java-app/demo
cd 
docker build . -t acrdemoarra.azurecr.io/demo/demo:1.1
docker push acrdemoarra.azurecr.io/demo/demo:1.1

az account set --subscription 1b97a195-dce8-46e0-a62a-b82047d37a6d


export RESOURCE_GROUP="myResourceGroup"
export LOCATION="swedencentral"
export CLUSTER_NAME="myAKSCluster"
export SERVICE_ACCOUNT_NAMESPACE="default"
export SERVICE_ACCOUNT_NAME="workload-identity-sa"
export SUBSCRIPTION="$(az account show --query id --output tsv)"
export USER_ASSIGNED_IDENTITY_NAME="myIdentity"
export FEDERATED_IDENTITY_CREDENTIAL_NAME="myFedIdentity"
# Include these variables to access key vault secrets from a pod in the cluster.
export KEYVAULT_NAME="keyvault-workload-id"
export KEYVAULT_SECRET_NAME="my-secret"

az group create --name "${RESOURCE_GROUP}" --location "${LOCATION}"
az aks create --resource-group "${RESOURCE_GROUP}" --name "${CLUSTER_NAME}" --enable-oidc-issuer --enable-workload-identity --generate-ssh-keys
export AKS_OIDC_ISSUER="$(az aks show --name "${CLUSTER_NAME}" --resource-group "${RESOURCE_GROUP}" --query "oidcIssuerProfile.issuerUrl" --output tsv)"
az identity create --name "${USER_ASSIGNED_IDENTITY_NAME}" --resource-group "${RESOURCE_GROUP}" --location "${LOCATION}" --subscription "${SUBSCRIPTION}"
export USER_ASSIGNED_CLIENT_ID="$(az identity show --resource-group "${RESOURCE_GROUP}" --name "${USER_ASSIGNED_IDENTITY_NAME}" --query 'clientId' --output tsv)"
az aks get-credentials --name "${CLUSTER_NAME}" --resource-group "${RESOURCE_GROUP}"

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: ServiceAccount
metadata:
  annotations:
    azure.workload.identity/client-id: "${USER_ASSIGNED_CLIENT_ID}"
  name: "${SERVICE_ACCOUNT_NAME}"
  namespace: "${SERVICE_ACCOUNT_NAMESPACE}"
EOF


az identity federated-credential create --name ${FEDERATED_IDENTITY_CREDENTIAL_NAME} --identity-name "${USER_ASSIGNED_IDENTITY_NAME}" --resource-group "${RESOURCE_GROUP}" --issuer "${AKS_OIDC_ISSUER}" --subject system:serviceaccount:"${SERVICE_ACCOUNT_NAMESPACE}":"${SERVICE_ACCOUNT_NAME}" --audience api://AzureADTokenExchange

cd /c/Work/java-sb-msi/aks
cd C:\Work\java-sb-msi\aks

kubectl apply -f java-app.yaml

kubectl delete -f java-app.yaml

kubectl logs javaapp

cat <<EOF | kubectl apply -f -
apiVersion: v1
kind: Pod
metadata:
  name: sample-workload-identity
  namespace: ${SERVICE_ACCOUNT_NAMESPACE}
  labels:
    azure.workload.identity/use: "true"  # Required. Only pods with this label can use workload identity.
spec:
  serviceAccountName: ${SERVICE_ACCOUNT_NAME}
  containers:
    - image: <image>
      name: <containerName>
EOF


--------------


https://learn.microsoft.com/en-us/azure/service-bus-messaging/service-bus-java-how-to-use-queues?tabs=passwordless