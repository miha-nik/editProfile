# About Project 
**EditProfile** application loads user information from the server and show it by going to 
edit mode the profile can be edited and saved, the changed data is sent to the server.

# Setup application
## Setup server (Optional)
For the application to work, you must use the server-side firebase code and database.
1. Create an empty application in sonsoli firebase.
2. Import the database (the test database is on the server / bd.json)
3. Firebase tools install: npm install -g firebase-tools
4. Run 'firebase login' to log in via the browser and authenticate the firebase tool.
5. Run 'firebase init' functions. The tool gives you an option to install dependencies with npm. It is safe to decline if you want to manage dependencies in another way.
6. Select firebase features: Functions
7. Instal dependencies for functions
8. Select project created in firebase console
9. Select language : Javascript
10. All other options select by defult
11. Put file index.js from server/functions to your functions folder
12. Run Deploy to server 'firebase deploy'

# Setup aplication
1) Clone project from github
2) (Optional) Setup url to your project on firebase. In app/build.gradle line 14 chahge correct path

# Run application
Build and run an application on a device or emulator

Application start load data from server and show it on first screan, push button edit to swich activity to edit profile.
If you want you can edit profile, after edit push button save, updateted info sent to bd on server.

# Libraries used in application

**Retrofit2** - used in the project for requests to the network

**Dagger2** - used to dependency injection

**Betternife** - for data binding

**RxJava** - for reactive programming

**Picasso** - for loading and showing current weather icon
