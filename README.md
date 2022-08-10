**Question #1 - REST** 

* Design a Restful API according to the requirements below.
* Write test cases for the API that you’ve designed.
* Code API automation for the test cases, using your choice of language and test framework.
* Include readme.md inside your project to describe how to execute the project.
* Design CI/CD and provide a config script for your work using your choice of tool. 

Requirements: 

At Pointr, we produce maps for large sites (eg. hospital campus) which typically consist of multiple buildings. So we need a series of APIs to exchange information with our CMS.

1. Site API
   * API to import a new Site
   * API to retrieve an existing Site
   * API to delete a Site
2. Building API
   * API to import Buildings
   * API to retrieve an existing Building
   * API to delete a Building
3. Levels API
   * API to import single or multiple levels
   

**Set up a project in Windows** 

I use JSON Server for creating an example of a database for sending request and receiving responses. This is a file "dataBase.json" at the root of the project
There are 3 tables created in advance and filled with examples of sites, buildings, levels.


To use my code you need:
1. To install Node.js:
* Go to https://nodejs.org/en/download/ and install it
* Copy the path to a folder where node.exe is installed (the end of a path should look like ".....Program Files\nodejs\\")
* Open System properties, Advanced tab (for setting environment paths)
* Then in System variables click Edit
* Find a variable Path, add a full path to Node that you copied in step №2
* Save changes
* To check if it is installed successfully open a terminal and type npm, you should see no errors
2. Install JSON server, put these commands in terminal: (all commands below are shown here https://github.com/typicode/json-server)
* npm install -g json-server
3. To start JSON server:
* In terminal go to a field where a file "db.json" is (it's at the root of the project) 
* In terminal type:  json-server --watch db.json
* Now if you go in browser to http://localhost:3000/Buildings, you'll get a list of all buildings


**Start the test**
* Open RunApiTests class
* Chose necessary methods with a @Test mark and run them
* 

**Question # 2 - UI**

1. Use a UI automation tool to validate the two requirements below are working as expected. You are free to use whatever language or test framework you want.
2. Include readme.md inside your project to describe how to execute the project. (DONE)
3. Design CI/CD and provide a config script for your work using your choice of tool.

Requirements:  

* Go to https://www.pointr.tech/blog (DONE)
* Verify that all articles have loaded.
* Find the most repeated 5 words in the latest 3 articles and save them (with their repeat count) into a .txt file. (DONE)
* Your implementation should support at least 2 browsers. (DONE)


**Start the test**  
* You should have a Chrome browser
* For starting a test via IDE open class "Blog" and press a button "start" next to a method "fiveMostUsedFords"
* The file with results will be created in project directory "src", the name is Results.txt

**Set up the project**

1. You can use Chrome or Firefox browser. For setting a browser:
* Open class "AbstractBaseTest"
* In method "setUp" change parameter @Optional. Put a variable USE_FIREFOX_BROWSER for using Firefox and USE_CHROME_BROWSER for using Chrome.
