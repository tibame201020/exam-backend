pipeline {
    agent {label "some-label"}
    stages {
        stage('Test') {
            steps {
                sh 'mvn clean package'
            }
        }
    }
}
