
pipeline {
    agent any
 tools { 
        maven 'Maven 3.8.6' 
        jdk 'jdk8' 
    }
    stages {

      
        stage ('Initialize') {
            steps {
              
                  sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
               
            }
        }
   
        stage('Build') {
            steps {
                echo 'Building..'
                sh 'mvn -Dmaven.test.failure.ignore=true install'
                 
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
                echo 'Hello, JDK'
                sh 'java -version'
            }
        }
    }
    

    
    
    
}
