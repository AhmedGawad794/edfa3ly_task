This project uses Java 15 and Maven 1.7, it's highly recommended to instal the same versions.

To run this script:
1. Instal IntelliJ IDEA Community Edition 2020.2.3 version.
2. Instal Java 15 & Maven 1.7 -you can instal them while instaling IntelliJ.
3. Create new maven project in Intellij.
4. Import these files to the project.
5. Download ChromeDriver.exe file from https://sites.google.com/a/chromium.org/chromedriver/downloads
- Make sure the version is compatible to your Chrome browser version -
6. In project folder, Create new folder with name "Resources" 
7. Move ChromeDriver.exe to "Resources Folder".
8. In POM.xml file in IntelliJ, Make sure the following dependencis are added:
<dependencies>
  
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-support</artifactId>
      <version>3.141.59</version>
    </dependency>
    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-chrome-driver</artifactId>
      <version>3.141.59</version>
    </dependency>
    <dependency>
      <groupId>org.testng</groupId>
      <artifactId>testng</artifactId>
      <version>6.14.3</version>
      <scope>test</scope>
    </dependency>
  </dependencies>
9. In the Maven menu, Click "Reload all maven projects" button.
10. From IntelliJ, Open Test Folder
11. In test folder, open CartPageTest class.
12. Run all tests in the class.
