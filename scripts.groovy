def test() {
    echo "executing pipeline fron branch $BRANCH_NAME"
    echo 'running tests'
}
def buildJar() {
    echo 'Building the application ...'
    sh 'mvn package'

}

def buildImage() {
    echo 'Building the docker image...'
    withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable:'PASS', usernameVariable:'USER')]) {
        sh 'docker build -t uzair102/u_repo:jma-2.1 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push uzair102/u_repo:jma-2.1'
    }
}

def deploy() {
      echo 'Deploying the application...'
}
return this
