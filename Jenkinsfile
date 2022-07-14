pipeline {
    agent any
    tools{
        maven "mvn"
    }
    stages {
        stage('Compile and validate'){
            steps{
                bat 'mvn clean compile validate'
            }
        }
        stage('package'){
          steps{
              bat 'mvn clean install package'
          }
        }
        stage('Build') {
            steps {
                bat 'mvn clean test'
            }
        }
    }
}
