def iShell(){
    cmd = ''
    while(true){
        try {
            cmd = input message: 'Enter command to run or enter exit for exit:', parameters: [string(defaultValue: cmd, description: '', name: 'cmd', trim: false)]
            if (cmd == 'exit'){
                break
            }
            result = sh([returnStdout: true, script: cmd]).trim()
            if(result){
                print result
            }
        } catch(err) {
            print err
        }
    }
}


pipeline {
  environment {
    imagename = "aviel1988/tsunami-glt:${env.BUILD_ID}"
    dockerImage = ''
  }
  agent any
  stages {

    stage('Checkout Source') {
      steps {
        git 'https://github.com/google/tsunami-security-scanner.git'
      }
    }
    
    // stage('debug') {
    //     steps{
    //         script{
    //             iShell()
    //         }
    //     }
    // }

    
    stage("Build image") {
        steps {
            // https://stackoverflow.com/questions/49299428/no-internet-connectivity-inside-docker-container-running-inside-kubernetes-with
            sh label: '', script: 'docker build -t $imagename --network container:$(docker ps | grep $(hostname) | grep k8s_POD | cut -d" " -f1) .'
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