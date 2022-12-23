
pipeline {
    agent any

    stages {
        stage ('Print') {
            steps {
                echo "Hello Devops Engineers"
            }
        }
        
        stage('Build') {
            steps {
                echo 'Building..'
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
