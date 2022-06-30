pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    tools{
        maven "mvn"
    }
    stages {
        // stage('Verify_browser_version') {
        //     steps{
        //         sh 'google-chrome --version'
        //         sh 'firefox --version'    
        //     }
        // }
        stage('Checkout') {
            steps {
                git url: 'https://github.com/CarlosM0918/Liverpool.git', branch: 'master'
            }            
        }
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
                // bat 'mvn clean test'
                // cd 'C:\\Users\\Azul\\Desktop\\material\\SeAvanzado\\Liverpool'
                // sh "chmod +x -R ${env.WORKSPACE}"
                // sh 'apt-get update'
                // sh 'apt-get install libglib2.0-0'
                // sh 'export LD_LIBRARY_PATH=$LD_LIBRARY_PATH:/usr/local/cuda/lib64/'
                sh 'mvn clean test'
                //sh 'false' // true
            }
        }
    }
}
