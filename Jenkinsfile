pipeline {
    agent {
        docker { image 'maven:3.8.4-jdk-17' }
    }
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
