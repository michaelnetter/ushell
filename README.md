[![Build](https://github.com/michaelnetter/ushell/actions/workflows/build.yml/badge.svg)](https://github.com/michaelnetter/ushell/actions/workflows/build.yml)
# μshell
A tiny remote shell written in Java.

I wrote this back in 2008 as a little fun project to play around with Java data streams. It simply connects the input and output streams of the server's native shell with a socket.

Use only for educational purposes. No responsibility is taken for any damage caused by this software.

## Usage
### Server
```bash
java -jar ushell.jar server
```
### Client
```bash
java -jar ushell.jar client <server-ip>
```
## License
This project is distributed under the Apache License, Version 2.0 (see LICENSE file).

By submitting a pull request to this project, you agree to license your contribution under the Apache License, 
Version 2.0.