# Delta communicator
> Messenger stylized by popular messengers

![Badge](https://img.shields.io/badge/status-in_progres-yellow.svg?style=for-the-badge)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Inspiration](#inspiration)
* [Contact](#contact)

## General info
Messenger stylized by popular messengers
## Technologies
* Spring - version 2.3.3.RELEASE
* Lombok - version 1.18.12
* MapStruct - version 1.4.0.Final
* Thymeleaf - version 3.0.11
* PostgresSQL - version 12.4

## Setup
This is background application for [communicator-java-front](https://github.com/IvanPlease/communicator-java-front) \
Create environment variables
* `JDBC_DATABASE_URL`
>Set address for postgres database
* `JDBC_DATABASE_USERNAME`
>Set username for postgres database
* `JDBC_DATABASE_PASSWORD`
>Set password for postgres database
* `MAIL_USERNAME`
>Set username for Google Gmail account
* `MAIL_PASSWORD`
>Set password for Google Gmail account

## Features
List of features ready and TODOs for future development
* Creating users from Google account data
* Creating conversations between users
* Sending message between users
* Daily emails with an amount of unread messages

To-do list:
* Async message detection
* Group message chats
* Sending attachments to groups

## Inspiration
Project inspired by Facebook's Messenger.

## Contact
Created by [@IvanPlease](https://github.com/IvanPlease)