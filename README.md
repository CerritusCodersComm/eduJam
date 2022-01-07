<h1 align="center">eduJam - Your one-stop to any university work</h1>

 
### This app was made under AndroidBuzz-TCET-Hackathon

<img src ="app/src/main/res/drawable/edujamicon.png" align="left"
width="200" hspace="10" vspace="10">

eduJam is an university app, which will help you with all of your university work. It has features which allow you to see the syllabus for the ongoing semester, weekly, academic and holiday time-table, to-do list, recent video lectures with respectives notes in a pdf form. We also have Fill my Cycle feature which helps in making the index for students under 2 minutes.
The app also includes a forum where all students can have a talk about any doubts.
To use eduJam, You will need a eduJam account* first, which you are going to use in this app.

Note: Currently eduJam is specifically for TCET students only.  
     
## Problem Statement:

Many issues have been occurred for students while learning online over time, whether it is a lack of effective communication between teachers and us or a lack of a proper one-stop for all of their work.
One of the challenges we face every day is to find lecture notes to study from, for the exams. The FAQ of a student is:- "Where is the zoom link?", "Whats the syllabus?", "Where are the recorded lectures?", are all solved by our application. It also makes bulky and boring tasks like making the indexes extremely easy.

## Proposed Solution :

This project "eduJam" proposes “one-stop for all university needs” to keep track of the college activities and student's learning and provides all the information about all of the syllabus, and timing of lectures, the one app where you have everything college related. It's features include having content like recorded lectures, notes, and modules, amd a very simple and automated FillMyCycle feature. It accepts student data allowing them to register to the application. This application has forums which allows the students to communicate with each other and collaborate or solve each others doubts extremely fast. Currently the app works for 2024 batch TCET students only. The project's scope is to extend it for other year batch students to allow for wider use and more universal appeal

### Screenshots
<img src ="assets/eduJam_collage.png" align="center">

### Demo Video
Click to go to youtube
[![Demo Video](https://img.youtube.com/vi/xL9uUAlAOyo/maxresdefault.jpg)](https://www.youtube.com/watch?v=xL9uUAlAOyo)

## Functionality & Concepts used

- The App has a very simple and interactive interface which helps the students in checking syllabus, notes, recent lectures etc. Following are few android concepts used to achieve the functionalities in app :

- Relative  Layout: it is used for making a Layout of some screens. It is easy to use since We need to make Elements relative to each other and its parent.
- Card Layout: Since we need to display a list of to-do items in CardView. The system provides the CardView API as an easy way for you to show information inside cards that have a consistent look across the platform. These cards have a default elevation above their containing view group, so the system draws shadows below them. 
- Navigation Package: To allow users to do different activities to use different features of the app.
- Navigation Component: Android Jetpack's Navigation component helps you implement navigation, from simple button clicks to more complex patterns, such as app bars and the navigation drawer. The Navigation component also ensures a consistent and predictable user experience by adhering to an established [set of principles](https://developer.android.com/guide/navigation/navigation-principles).
- View Binding: ViewBinding is always null safe and type-safe. ViewBinding also helps to reduce the boilerplate code, hence reducing the code redundancy. Using ViewBinding the compilation of the code is a bit faster as compared to the traditional findViewById() method.
- ViewModel: The ViewModel class is designed to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows data to survive configuration changes such as screen rotations.
- LiveData & Room Database: LiveData only updates app component observers that are in an active lifecycle state. We are using LiveData to update & observe any changes in the to-do list and update it to local databases using Room. Room database is also used to create, delete, read and update in local storage.
- Firebase Authentication: Firebase email authentication is used to add email signup and login functionality to the app. Firebase sdk for Android has all relevant functionality needed to add it.
- Firebase Firestore: It is used as a primary database for the app. It consists data of users, notifications and forum interactions.
- Firebase Cloud Messaging: It is used to display notifications in the app. Firebase admin sdk is used to make an API for sending customized notifications with title and body.
- Heroku: Heroku is used to host the REST API used to send notifications.
- SharedPreferences: It is used to persist user login on device.
- Lottie Animations: A Lottie is a JSON-based animation file format that enables designers to ship animations on any platform as easily as shipping static assets. They are small files that work on any device and can scale up or down without pixelation.

## Application Link & Future Scope :
Talking about the usability of the app, you can try out beta version for now from [here](https://github.com/cdhiraj40/eduJam/blob/main/app/release/app-release.apk) or [here](https://drive.google.com/file/d/1reBSs83vkvdV73bD6lNm_oGxRxU8HQ4v/view?usp=sharing).

About the future usage of our app, we plan to make it accessibale to every student in our university. We are also thinking of collaborating with teachers and with neighboring colleges to propose this app idea and collaborate with them. We aim that once students will start using the app, we will be able to make it way better than what it is now. Also we have planned many features for are app so that it can be made more robust and user friendly.



