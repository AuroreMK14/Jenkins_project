def buildJar() {
    echo 'building the application...'
    sh 'mvn package'
}

def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t auroremk/demo-app .'
        sh 'echo $PASS | docker login -u $USER --password-stdin'
        sh 'docker push auroremk/demo-app'
    }
}

def deployApp() {
    echo 'deploying the application...'
}

return this
