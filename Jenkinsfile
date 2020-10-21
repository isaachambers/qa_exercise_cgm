pipeline {
    agent { dockerfile true }

    stages {
        stage('Build & Test') {
            steps {
                sh './gradlew build'
                sh './gradlew test jacocoTestReport'
            }
        }
    }
}