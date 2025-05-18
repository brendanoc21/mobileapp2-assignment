# Mobile App Development 2 - Assignment 2
## Property Auction App
_Brendan O'Connor (20098888)_
___
### Features
___
### Users
- Multi-user support
- Each property is assigned to the user that created it
- Authentication through Firebase
- Login via email and password
- Login via Google sign in
- Change user profile picture

### CRUD
- Create Properties for auctioning with 5 fields
 - Property Type : String from selection of 3 options
 - Price: Int selected from scrolling list
 - Size: String selected from scrolling list
 - Rentable: Boolean selected from True or False
 - Details: String from user input text box
- Read list of properties in card list
- Update property Details from show more page
- Delete properties from card list
- Properties are persisted through Firestore

### Map
- View map with properties shown
- Properties store location they were created at

### Navigation
- Property list, Map and Search screens are available via bottom app bar
- Profile and About screens can be accessed by drop down menu in top app bar
- Add Property screen accessed from icon in top app bar
- Back button available in top app bar

### Attempted Features
- Display all Properties function works but can only be adjusted from the source code, a switch in the top app bar successfully toggles the value but does not change display
- Search screen to directly open a property with matching details was attempted but was not completed
  
#### Sources
Source of House Sketch
https://www.pinterest.com/pin/how-to-draw-a-house-in-one-point-perspective-youtube--465700417731687712/
