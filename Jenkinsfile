pipeline {
    agent any
    tools{
        maven "mvn"
        dockerTool 'docker'
    }
    stages {
        // stage('Compile and validate'){
        //     steps{
        //         bat 'mvn clean compile validate'
        //     }
        // }
        // stage('package'){
        //   steps{
        //       bat 'mvn clean install package'
        //   }
        // }
        stage('Build') {
            steps {
                bat 'docker build -f p3.Dockerfile -t liverpool .'

                bat 'docker run --name lpool liverpool'
            }
        }
    }
}
