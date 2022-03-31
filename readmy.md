**Task №2**

1. Use a UI automation tool to validate the two requirements below are working as expected. You are free to use whatever language or test framework you want.
2. Include readme.md inside your project to describe how to execute the project.
3. Design CI/CD and provide a config script for your work using your choice of tool.

Requirements:  

* Go to https://www.pointr.tech/blog
* Verify that all articles have loaded. (in progress, what exactly means loaded, check number of pages\links or open each article and check number of symbols?)
* Find the most repeated 5 words in the latest 3 articles and save them (with their repeat count) into a .txt file. (done)
* Your implementation should support at least 2 browsers. (done)

**set up the project**

1.Set a path to a directory where a new .txt file with results will be created. 
* Open class "BlogPage" 
* In a variable PATH_TO_FILE_WITH_RESULTS type a path (Example "C:\...\...\nameOfFile.txt")
* Please, make sure that a name of the file is unique.

2. Set paths to drivers (you will need Chrome and Geckodriver)
* Open class "AbstractBaseTest"
* In a variables PATH_TO_CHROME_DRIVER and PATH_TO_FIREFOX_DRIVER type a path to corresponding drivers, that should be saved at your PC (Example "C:/.../.../chromedriver.exe")

3.Set a browser you would like to use: Chrome or Firefox:
* Open class "AbstractBaseTest"
* In method "setUp" change parameter @Optional. By default a Chrome is used. If you need to change it to Firefox you should put a variable USE_FIREFOX_BROWSER for using Firefox. Use a variable USE_CHROME_BROWSER for using Chrome.

**start the test**  

For starting a test via IDE you should open class "Blog" and press a button "start" next to a method "fiveMostUsedFords"