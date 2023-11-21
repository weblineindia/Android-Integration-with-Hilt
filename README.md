# Android - Hilt Demo

A Android based Hilt Integration Demo. 


## Table of contents
- [Android Support](#android-support)
- [Demo](#demo)
- [Features](#features)
- [Getting started](#getting-started)
- [Usage](#usage)
- [Methods](#methods)
- [Want to Contribute?](#want-to-contribute)
- [Need Help / Support?](#need-help)
- [Collection of Components](#collection-of-Components)
- [Changelog](#changelog)
- [License](#license)
- [Keywords](#Keywords)


## Android Support

Version - Android 14

We have tested our program in above version, however you can use it in other versions as well.


## Demo
![](./hilt_demo.gif)


## Features

* Hilt integration with MVVM clean architecture.


## Getting started

* Download this sample project and import widget dart files in your Android App. 
* Update Widgets UI based on your requirements. 


## Usage

Setup process is described below to integrate in sample project.

### Methods

Gradle Dependencies
      
    implementation("com.google.dagger:hilt-android:2.48.1")
    kapt("com.google.dagger:hilt-compiler:2.48.1")
    
Annotations
    
    @HiltAndroidApp : Annotation to declare Hilt Application
    @Module : Annotation to declare Module
    @InstallIn : Annotation to declare which Hilt generated DI contains
    @Singleton : Identifies a type that the injector only instantiates once
    @Provides : annotation require to return feature of each module method
    @HiltViewModel : enable injection of a ViewModel by Hilt
    @AndroidEntryPoint : enable members injection in activity
    @Inject : annotation lets us define an injection point that is injected during bean instantiation

Further implementation you can do as per requirement and usage


## Want to Contribute?

- Created something awesome, made this code better, added some functionality, or whatever (this is the hardest part).
- [Fork it](http://help.github.com/forking/).
- Create new branch to contribute your changes.
- Commit all your changes to your branch.
- Submit a [pull request](http://help.github.com/pull-requests/).

 
## Collection of Components
 We have built many other components and free resources for software development in various programming languages. Kindly click here to view our [Free Resources for Software Development.](https://www.weblineindia.com/software-development-resources.html)
 

## Changelog
Detailed changes for each release are documented in [CHANGELOG](./CHANGELOG).


## License
[MIT](LICENSE)

[mit]: ./LICENSE


## Keywords
Android-Hilt, Android-Dependency-Injection, Dependency-Injection, Hilt-Dependency-Injection
