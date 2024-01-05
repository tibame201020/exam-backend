pipeline {
    agent {label "maven-jdk17"}
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
