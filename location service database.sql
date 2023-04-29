drop table if exists Visits;
drop table if exists Visited_Locations;
drop table if exists Users;
drop table if exists Historical_Events;
drop table if exists Locations;

CREATE TABLE Locations (
  id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) UNIQUE KEY,
  description VARCHAR(255),
  picture VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE
);

ALTER TABLE Locations AUTO_INCREMENT=1;

CREATE TABLE Users (
  id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(255),
  password VARCHAR(255),
  email VARCHAR(255),
  latitude DOUBLE,
  longitude DOUBLE
);

CREATE TABLE Visited_Locations (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  location_id INT,
  date_visited DATETIME,
  FOREIGN KEY (user_id) REFERENCES Users(id),
  FOREIGN KEY (location_id) REFERENCES Locations(id),
  UNIQUE (user_id, location_id)
);

-- Insert sample locations
INSERT INTO Locations (name, description, picture, latitude, longitude) VALUES
('Bobby Dodd Stadium', 'Home of the Georgia Tech football team and host of various events, including concerts, soccer games, and the Peach Bowl.', 'https://i.imgur.com/P7yvN2Q.jpg', 33.7721, -84.3923),
('Ferst Center for the Arts', 'Performing arts center on campus, featuring various concerts, plays, and other performances.', 'https://i.imgur.com/f6mtzR6.jpg', 33.7753, -84.3969),
('The Varsity', 'Famous Atlanta fast-food restaurant chain, serving up hot dogs, burgers, and fries since 1928.', 'https://i.imgur.com/T2XvQHx.jpg', 33.7718, -84.3892),
('Georgia Tech Library', 'The Georgia Tech Library is the main library on campus, featuring a wide range of resources and study spaces.', 'https://i.imgur.com/SmhMWfR.jpg', 33.7749, -84.3963),
('Klaus Advanced Computing Building', 'Home to Georgia Tech\'s College of Computing, the Klaus Advanced Computing Building features cutting-edge research labs and classrooms.', 'https://i.imgur.com/VuV2zpe.jpg', 33.7775, -84.3985),
('Robert C. Williams Paper Museum', 'Museum on campus dedicated to the history and science of papermaking.', 'https://i.imgur.com/WXJv9xH.jpg', 33.7748, -84.3921),
('Tech Tower', 'Iconic building on campus, built in 1888 and home to the administration of Georgia Tech.', 'https://i.imgur.com/qfzoEVe.jpg', 33.7738, -84.3965),
('Carnegie Building', 'Historic building on campus, built in 1907 and named after Andrew Carnegie.', 'https://i.imgur.com/PsUJ23E.jpg', 33.7745, -84.3961),
('Campanile', 'Clock tower on campus, built in 1995 and standing 226 feet tall.', 'https://i.imgur.com/PDmugKC.jpg', 33.7725, -84.3945),
('Tech Walkway', 'Main pedestrian walkway on campus, stretching from Bobby Dodd Stadium to the Student Center. It was originally named Fowler Street and was later renamed to Tech Walkway.', 'https://i.imgur.com/Z8Yv70T.jpg', 33.7729, -84.3938),
('West Village', 'Mixed-use development on campus featuring various retail stores, restaurants, and apartments. West Village was originally built as a housing complex for athletes during the 1996 Olympics.', 'https://i.imgur.com/ynYzKjV.jpg', 33.7810, -84.4049),
('Engineered Biosystems Building', 'Research facility on campus focused on bioengineering and bioscience. The building features a green roof that helps with insulation and stormwater management.', 'https://i.imgur.com/bvA0T65.jpg', 33.7751, -84.3974),
('Ford ES&T Building', 'Research facility on campus focused on environmental science and engineering. The building was designed with sustainability in mind and features a rainwater harvesting system.', 'https://i.imgur.com/xHJzDKQ.jpg', 33.7752, -84.3977),
('Boggs Chemistry Building', 'Research facility on campus focused on chemistry and chemical biology. The building is named after George Boggs, a former professor and chair of the School of Chemistry and Biochemistry.', 'https://i.imgur.com/HsmG0l7.jpg', 33.7751, -84.3968),
('Skiles Classroom Building', 'Classroom building on campus featuring various lecture halls and study spaces. The building was named after George Skiles, a former professor and dean of the College of Sciences.', 'https://i.imgur.com/b32iQzF.jpg', 33.7762, -84.3978),
('Molecular Science and Engineering Building', 'Research facility on campus focused on molecular science and engineering. The building features a nanofabrication facility and an advanced imaging center.', 'https://i.imgur.com/qOJN8lr.jpg', 33.7742, -84.3975),
('Scheller College of Business', 'Building on campus housing the Scheller College of Business, featuring classrooms and study spaces. The College of Business was named after Ernest Scheller, a former CEO of the global packaging company, Silgan Holdings.', 'https://i.imgur.com/k5kscH5.jpg', 33.7765, -84.3945),
('Guggenheim School of Aerospace Engineering', 'Building on campus housing the School of Aerospace Engineering, featuring classrooms and research facilities. The School of Aerospace Engineering was named after Daniel Guggenheim, a philanthropist who donated funds to establish it.', 'https://i.imgur.com/UVLbNyJ.jpg', 33.7764, -84.3877),
('Clough Undergraduate Learning Commons', 'Academic building on campus featuring various study spaces, including a rooftop garden. The building is named after the former president of Georgia Tech, Wayne Clough.', 'https://i.imgur.com/TUvROVJ.jpg', 33.7765, -84.3946),
('Architecture Building', 'Academic building on campus housing the School of Architecture. The building features various design studios and classrooms.', 'https://i.imgur.com/JPo6U5N.jpg', 33.7765, -84.3986),
('Alumni House', 'Building on campus housing the Georgia Tech Alumni Association. The building features various event spaces and hosts alumni events throughout the year.', 'https://i.imgur.com/yxtTKJF.jpg', 33.7769, -84.3904),
('Student Center', 'Student hub on campus featuring various dining options, event spaces, and meeting rooms. The building is home to the Georgia Tech Bookstore and the Campus Recreation Center.', 'https://i.imgur.com/Dmqe8yj.jpg', 33.7749, -84.3963),
('Old Civil Engineering Building', 'Historic building on campus housing the School of Civil and Environmental Engineering. The building was constructed in 1897 and is the oldest academic building on campus.', 'https://i.imgur.com/ysKru8G.jpg', 33.7729, -84.3919),
('Van Leer Electrical and Computer Engineering Building', 'Building on campus housing the School of Electrical and Computer Engineering. The building features various research labs and classrooms, including a clean room for microfabrication.', 'https://i.imgur.com/fAaDVp9.jpg', 33.7763, -84.3972),
('Georgia Tech Hotel and Conference Center', 'Hotel and conference center on campus featuring various amenities, including a rooftop pool and restaurant. The hotel is operated by the Georgia Tech Hotel and Lodging Association.', 'https://i.imgur.com/78n9Kai.jpg', 33.7774, -84.3885),
('Tech Green', 'Open space on campus featuring various sculptures and artwork, including a large metal sphere titled "The Solar Tree". The area is often used for student events and activities.', 'https://i.imgur.com/iHTO21o.jpg', 33.7741, -84.3941),
('Invention Studio', 'Student-run makerspace on campus featuring various tools and equipment for student use. The studio is open 24/7 and is staffed by student volunteers.', 'https://i.imgur.com/PxbgkW9.jpg', 33.7766, -84.4007),
('West Architecture Building', 'Academic building on campus housing the School of Architecture. The building features various design studios and classrooms, including a digital fabrication lab.', 'https://i.imgur.com/wY1sKsC.jpg', 33.7761, -84.3999),
('Ford Environmental Science and Technology Building', 'Building on campus housing the School of Earth and Atmospheric Sciences. The building features various research labs and classrooms, including a weather observatory on the roof.', 'https://i.imgur.com/mgwbawQ.jpg', 33.7749, -84.3959),
('Howey Physics Building', 'The Howey Physics Building is home to Georgia Tech’s School of Physics. It was named after a prominent physicist and professor at Tech, Dr. Paul Howey.', 'https://www.physics.gatech.edu/sites/default/files/images/HoweyBuilding_main.jpg', 33.777304, -84.396581),
('CRC Fields', 'The Campus Recreation Center Fields are a popular spot for intramural sports and pickup games, with four turf fields that can be used for soccer, ultimate frisbee, and more.', 'https://crc.gatech.edu/sites/default/files/styles/panopoly-image-full/public/2019-02/CRC%20fields%20pano.jpg?itok=NXyNcwFJ', 33.775475, -84.407967),
('Stamps Health Services', 'Stamps Health Services provides healthcare to all Georgia Tech students, offering services such as primary care, psychiatry, physical therapy, and more.', 'https://www.scheller.gatech.edu/images/tech-square.jpg', 33.775406, -84.398921),
("The Georgia Tech Seal", "The official seal of the Institute, featuring a depiction of the steam engine The General.", "https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/GeorgiaTechSeal.svg/1200px-GeorgiaTechSeal.svg.png", 33.773797, -84.394698),
("The Georgia Tech Water Tower", "A historic landmark on the Georgia Tech campus, originally built in 1896 to provide water for the newly established school.", "https://upload.wikimedia.org/wikipedia/commons/4/4c/GATechWaterTower.jpg", 33.773518, -84.396532),
("The Georgia Tech Trolley", "A restored 1912 streetcar that now serves as a museum and gift shop. It was donated to the Institute in 1983.", "https://upload.wikimedia.org/wikipedia/commons/f/fd/Georgia_Tech_Trolley_1.jpg", 33.774822, -84.396592),
("The Georgia Tech Steam Whistle", "A steam whistle that signals the start and end of each work day on campus. It was originally used as a train whistle.", "https://upload.wikimedia.org/wikipedia/commons/4/4d/Georgia_Tech_Steam_Whistle.jpg", 33.772254, -84.394671),
("The Lettie Pate Whitehead Evans Administration Building", "A historic building on the Georgia Tech campus that serves as the administrative center of the Institute. It was built in 1947 and named after philanthropist Lettie Pate Whitehead Evans.", "https://upload.wikimedia.org/wikipedia/commons/8/8b/LPW_Building_Georgia_Tech.jpg", 33.773525, -84.396666),
('Bobby Dodd Stadium Statue', 'Bronze statue of Georgia Tech football coach Bobby Dodd, located outside the stadium that bears his name', 'https://i.imgur.com/fy4Jks4.jpg', 33.7713, -84.3924),
('Buzz and Skippy Statue', 'Bronze statue of Georgia Tech mascot Buzz and his canine sidekick Skippy, located outside McCamish Pavilion', 'https://i.imgur.com/UMywA2M.jpg', 33.7752, -84.3964),
('Engineers without Borders Memorial', 'Memorial plaque dedicated to Georgia Tech students and faculty who have contributed to the Engineers without Borders program', 'https://i.imgur.com/L6XfL6e.jpg', 33.7753, -84.3965),
('Ernest G. Welch Statue', 'Bronze statue of Georgia Tech alumnus and benefactor Ernest G. Welch, located outside the College of Design', 'https://i.imgur.com/3YhSswS.jpg', 33.7736, -84.3982),
('John Heisman Trophy', 'Bronze statue of former Georgia Tech football coach John Heisman, located outside the Callaway Building', 'https://i.imgur.com/UmdC5nJ.jpg', 33.7721, -84.3932),
('Lettered Rock', 'Rock with a "GT" carving, located in the center of the Skiles Walkway', 'https://i.imgur.com/WZ68q3i.jpg', 33.7773, -84.3961),
('Michael A. Kahn Statue', 'Bronze statue of Georgia Tech alumnus and benefactor Michael A. Kahn, located outside the College of Computing', 'https://i.imgur.com/3MqQLjK.jpg', 33.7736, -84.3957),
('Monument to Civil Rights', 'Monument dedicated to Georgia Tech\'s role in the civil rights movement and the "Atlanta Student Movement" of the 1960s', 'https://i.imgur.com/XiJcvZM.jpg', 33.7767, -84.3973),
('Ramblin\' Wreck Statue', 'Bronze statue of the Ramblin\' Wreck, Georgia Tech\'s beloved 1930 Ford Model A Sport Coupe mascot, located outside the Student Center', 'https://i.imgur.com/sdOYnw2.jpg', 33.7745, -84.3968),
('Robert C. Williams Museum of Papermaking Statue', 'Statue of a papermaker, located outside the Robert C. Williams Museum of Papermaking', 'https://i.imgur.com/VILtD1C.jpg', 33.7751, -84.4012),
('The Thinker Statue', 'Bronze statue of The Thinker by Auguste Rodin, located outside the College of Business', 'https://i.imgur.com/z4xdIZ6.jpg', 33.7742, -84.3962),
('War Memorial', 'Memorial plaque dedicated to Georgia Tech students and alumni who have died in service to their country', 'https://i.imgur.com/hfIm0zG.jpg', 33.7737, -84.3979),
('Whistle Statue', 'Bronze statue of the whistle used to start Georgia Tech football games, located outside the Student Center', 'https://i.imgur.com/R7fGBsy.jpg', 33.7746, -84.453);

INSERT INTO Users (username, password, email) VALUES
('alma', '12345','nkemlaalma@gmail.com');
