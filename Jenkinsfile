
pipeline {
    agent any
 environment {
    POM_GROUP_ID = readMavenPom().getGroupId()
    POM_VERSION = readMavenPom().getVersion()
   
  }
    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                 echo "$POM_GROUP_ID"
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
    
      post {
        always { 
            echo 'I will always say Hello again!'
        }
        success {
            echo 'I will say Hello only if job is success'
        }
        failure {
            echo 'I will say Hello only if job is failure'
        }
    }
    
    
    
}
