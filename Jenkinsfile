void setBuildStatus(String message, String state) {
  step([
      $class: "GitHubCommitStatusSetter",
      reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/isaachambers/qa_exercise_cgm.git"],
      contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
      errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
      statusResultSource: [ $class: "ConditionalStatusResultSource", results: [[$class: "AnyBuildResult", message: message, state: state]] ]
  ]);
}

pipeline {
    agent { dockerfile true }
    triggers {
            pollSCM '* * * * *'
    }
    stages {
        stage('Build & Test') {
            steps {
                sh 'chmod +x gradlew'
                sh './gradlew build'
                sh './gradlew test jacocoTestReport'
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