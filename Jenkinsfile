pipeline {
    agent any
    tools{
        maven "mvn"
    }
    stages {
        stage('Build') {
            steps {
                bat 'docker build -f p3.Dockerfile -t liverpool .'

                bat 'docker run --name lpool liverpool'
            }
        }
    }
}
