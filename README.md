📌 Dance School Management System

	A Java-based client–server application for managing dance courses, students, certificates, and registrations.

🚀 Overview

	This project is a complete management system for a dance school, developed using:

		Java (Swing + custom UI enhancements)
		Client–Server architecture
		MariaDB for database storage
		DAO pattern, MVC, and custom domain models

	The application supports data management for:

		Students and dance instructors
		Types of dances
		Skill levels
		Certificates
		Course registrations
		Searching, filtering, adding, editing and deleting entries

	The system consists of three separate Java modules:

		Klijent (client) - Graphical user interface (Swing)
		Server (server) - Communication + database operations
		Zajednicki (common) - Shared domain classes and transfer objects

🗄 Database

	The project uses MariaDB.

	To import the database:

		Open MariaDB / MySQL client
		Run: SOURCE danceschool.sql;

	The exported SQL file is located in:
		/database/danceschool.sql

🧩 Features

	✔ Add, edit, search and delete students
	✔ Register students for dance courses
	✔ Manage dance categories and skill levels
	✔ Insert certificates for each dance instructor
	✔ Client–server communication via sockets
	✔ Custom UI styling (colors, fonts, consistent layout)
	✔ Validation of user input
	✔ Error handling + confirmation dialogs

🛠 Technologies Used

	Java SE 21
	Swing (UI)
	10.4.32-MariaDB
	SQLyog (database admin)
	NetBeans IDE 14
	Object-Oriented Architecture

📎 Project Structure

DanceSchoolApp/
│
├── klijent/       # UI forms, controllers
├── server/        # Server logic + database communication
├── zajednicki/    # Model classes (Ples, Nivo, Polaznik...)
├── database/      # SQL dump + import instructions
└── README.md

👩‍💻 Author

Marija Simović
Master student – Software Engineering & Artificial Intelligence
University of Belgrade, Faculty of Organizational Sciences

Feel free to reach out or explore the project!