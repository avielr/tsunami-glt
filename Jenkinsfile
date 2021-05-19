pipeline {
  environment {
    imagename = "aviel1988/tsunami-glt:${env.BUILD_ID}"
    dockerImage = ''
  }
  agent any
  stages {

    stage('Checkout Source') {
      steps {
        git url:'https://github.com/google/tsunami-security-scanner.git', branch:'master'
      }
    }
    
      stage("Build image") {
            steps {
                script {
                    myapp = docker.build imagename
                }
            }
        }
    
      stage("Push image") {
            steps {
                script {
                    docker.withRegistry('https://registry.hub.docker.com', 'dockerhub') {
                            myapp.push("latest")
                            myapp.push("${env.BUILD_ID}")
                    }
                }
            }
        }

    // stage('Deploy App') {
    //   steps {
    //     script {
    //       kubernetesDeploy(configs: "hellowhale.yml", kubeconfigId: "mykubeconfig")
    //     }
    //   }
    // }
  }
}