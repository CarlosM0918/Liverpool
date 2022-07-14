pipeline {
    agent any
    tools{
        maven "mvn"
    }
    stages {
        stage('Compile and validate'){
            steps{
                sh 'mvn clean compile validate'
            }
        }
        stage('package'){
          steps{
              sh 'mvn clean install package'
          }
        }
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}
