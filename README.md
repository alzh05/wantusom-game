<p id="readme-top"></p>

# WanTuSom

## About The Project

WanTuSom is a simple Java project inspired by the classic Rock-Paper-Scissors game. This project was created because I wanted to learn the fundamental concepts of Java programming, including control flow structures, user input handling, object-oriented programming and basic game logic implementation.

### Game Features 

- Four moves - `Bird`, `Stone`, `Water`, and `Fire`
- Two game modes - `Player vs Computer` and `Player vs Player`
- Sudden death round
- Score tracking
- Play-again option
- Interactive UI
- Input validation

### Game Rules

1. **Win conditions**:
   
   - `Bird` beats `Water`
   - `Stone` beats `Bird`
   - `Water` beats `Stone`
   - `Fire` defeats all
     
2. **Match structure**:
   
   - One match consists of 4 rounds.
   - If scores are tied after 4 rounds, sudden death round begins.
  
3. **Restrictions**:
   
   - Each player can use `Fire` only twice per match.
   - `Fire` is banned in sudden death round.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Getting Started

### Prerequisites

1. **Java SE Development Kit (JDK) version 8 or later**

   - Download JDK from [Oracle](https://www.oracle.com/java/technologies/downloads) or [OpenJDK](https://jdk.java.net).
   - To check if JDK is installed on your local machine, open your terminal and run the following commands:
     ```bash
     java -version
     javac -version
     ```
     - If both commands return version information, JDK is properly installed.
     - If either command is not recognized, JDK may not be installed correctly.

### Installation

**Option 1: Using Git (Recommended)**

1. Open your terminal.
2. Navigate to the directory where you want the project root directory to be located.
3. Run the following command to clone the repository to your local machine:
   ```bash
   git clone https://github.com/alzh05/wantusom-game.git
   ```

**Option 2: ZIP Download**

1. Go to the repository on GitHub: [https://github.com/alzh05/wantusom-game](https://github.com/alzh05/wantusom-game)
2. Click the green "Code" button.
3. Select "Download ZIP" to download the project as a ZIP file.
4. Extract the ZIP file to a directory of your choice.

### How to Compile and Run

1. Open your terminal.
2. Navigate to the project root directory.
3. Run the following command to compile all `.java` files into `.class` files and store them in the `bin` directory:
   ```bash
   javac -d bin src/io/github/alzh05/wantusom/*.java
   ```
4. After successful compilation, run the following command to launch the program:
   ```bash
   java -cp bin io.github.alzh05.wantusom.Main
   ```


<p align="right">(<a href="#readme-top">back to top</a>)</p>


## License

Distributed under the MIT License. See `LICENSE` for more information.


<p align="right">(<a href="#readme-top">back to top</a>)</p>


## Contact

**Adrian Lee**
- GitHub: [@alzh05](https://github.com/alzh05)
- Email: [adrianleezh12@gmail.com](mailto:adrianleezh12@gmail.com)
- Project Link: [https://github.com/alzh05/wantusom-game](https://github.com/alzh05/wantusom-game)


<p align="right">(<a href="#readme-top">back to top</a>)</p>
