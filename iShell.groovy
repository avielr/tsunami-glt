def iShell(){
    cmd = ''
    while(true){
        try {
            cmd = input message: 'Enter command to run or enter exit for exit:', parameters: [string(defaultValue: cmd, description: '', name: 'cmd', trim: false)]
            if (cmd == 'exit'){
                break
            }
            result = sh([returnStdout: true, script: cmd]).trim()
            if(result){
                print result
            }
        } catch(err) {
            print err
        }
    }
}