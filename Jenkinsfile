void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/isaachambers/qa_exercise_cgm"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {
    agent any
    triggers {
        pollSCM '* * * * *'
    }

    environment {
      dockerImage = ''
      GIT_HASH = sh(returnStdout: true, script: "git rev-parse HEAD").trim()
      DOCKER_UNIQUE_TAG = "${env.GIT_HASH}-${env.BUILD_NUMBER}"
    }

    stages {
        stage('Prepare') {
            steps {
              script {
                     sh "rm -rf *"
                     checkout scm
              }
            }
        }
        stage('Build & Test') {
            steps {
              script {
                     try {
                          sh 'chmod +x gradlew'
                          sh './gradlew build -x test --no-daemon migrateDev'
                          sh './gradlew test jacocoTestReport --no-daemon'
                      } finally {
                          junit '**/build/test-results/test/*.xml'
                      }
               }
            }
        }
        stage('Publish Test Coverage Report') {
            steps {
              step([$class: 'JacocoPublisher',
                     execPattern: '**/build/jacoco/*.exec',
                     classPattern: '**/build/classes',
                     sourcePattern: 'src/main/java',
                     exclusionPattern: 'src/test*'
                     ])
            }
        }
        stage('Build Image') {
            steps {
               script {
                   dockerImage = docker.build registry + ":$DOCKER_UNIQUE_TAG"
               }
            }
        }
        stage('Cleaning up') {
            steps {
               sh "docker rmi $registry"
            }
        }
    }
    post {
        cleanup {
          deleteDir()
        }
        success {
            setBuildStatus("Build succeeded", "SUCCESS");
        }
        failure {
            setBuildStatus("Build failed", "FAILURE");
        }
    }
}
