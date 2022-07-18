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
        stage('Build') {
            steps {
                bat 'mvn clean test -DtestngFile=RemoteSuite.xml'
            }
        }
    }
}
