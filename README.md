# MAD_Project1_ITFS

### Ireland Tourism Forecast System
### Name: Michael Pound, 20085540

----------------------------------------------------------------------

## Outline:
This project allows users to add their own favourite Tourist Spots around Ireland. All the Tourist Spots are saved in JSON. All Tourist Spots can be updated/deleted. There is filtering and searching for browsing all the Tourist Spots. The user can then view the current weather status for a selected Tourist Spot.

The is two versions runnable, a CLI version and a GUI version.

----------------------------------------------------------------------

## CLI Features:
 
 + Feature 1 = Add Tourist Spot.
 + Feature 2 = List all Tourist Spots.
 + Feature 3 = Update a Tourist Spot.
 + Feature 4 = Delete a Tourist Spot.
 + Feature 5 = Get the weather outline for a Tourist Spot.
 + Feature 6 = Get the full weather for a Tourist Spot.
 + Feature 7 = Search all fields of all stored Tourist Spots.
 + Feature 8 = Filter Tourist Spots by County.

### Main CLI Menu:

![][cliMenu]

### Option 1:
Prompts the user to enter a value for each field and then creates a new Tourist Spot.

![][cliOption1]

### Option 2:
Lists all Tourist Spots.

![][cliOption2]

### Option 3:
User enters ID of Tourist Spot and then can update the fields they wish to.

![][cliOption3]

### Option 4:
User enters ID of Tourist Spot they wish to delete and then confirms.

![][cliOption4]

### Option 5:
User enters ID of Tourist Spot and is given a weather outline.

![][cliOption5]

### Option 6:
User enters ID of Tourist Spot and is given a detailed weather report.

![][cliOption6]

### Option 7:
User can search all fields of all Tourist Spots.

![][cliOption7]

### Option 8:
This option will display all Counties of the currently stored Tourist Spots and list all spots below. The user can enter the name of a County and then that County will be removed/included in the filter. This can be seen by either the X. for not included and O. for included.

![][cliOption8]

----------------------------------------------------------------------

## GUI Features:
 
 + Feature 1 = List all Tourist Spots.
 + Feature 2 = Add Tourist Spot.
 + Feature 3 = Update a Tourist Spot.
 + Feature 4 = Delete a Tourist Spot.
 + Feature 5 = Get the full weather for a Tourist Spot.


### Main GUI View:

This displays a list of all Tourist Spots saved.
Users can right-click on each Tourist Spot and is prompted with options.
The plus icon up the top allows the user to navigate to the add Tourist Spot view.

![][guiList]

### Right-Click view:
This allows users to navigate to update view and weather view.

![][guiRightClick]

### Adding Tourist Spot View:
Allows the user to enter all fields and add a Tourist Spot using the save icon on the top.

![][guiAdd]

### Updating Tourist Spot View:
Allows the user to update any views and then save the new fields using the save icon or the user can delete the Tourist Spot using the bin icon.

![][guiUpdate]

### Weather View:
Displays the full weather details for the currently selected Tourist Spot.

![][guiWeather]

## Known Issues:

 + Double-Clicking on the GUI table views returns an error.
 + Some values for longitude and latitude cause issues with the weather API.
 + Extra features that are in the CLI are not in the GUI, this is because TornadoFX was causing massive issues.

## API Key:

I have a file that is not included in the git repo, this file contains my API key for Open Weather Map API.
This can be seen here for those that wish to replicate it:

![][fileView] ![][apiKey]



[cliMenu]: ./images/cli_menu.png
[cliOption1]: ./images/cli_option_1.png
[cliOption2]: ./images/cli_option_2.png
[cliOption3]: ./images/cli_option_3.png
[cliOption4]: ./images/cli_option_4.png
[cliOption5]: ./images/cli_option_5.png
[cliOption6]: ./images/cli_option_6.png
[cliOption7]: ./images/cli_option_7.png
[cliOption8]: ./images/cli_option_8.png

[guiList]: ./images/gui_list.png
[guiRightClick]: ./images/gui_right_click.png
[guiAdd]: ./images/gui_add.png
[guiUpdate]: ./images/gui_update.png
[guiWeather]: ./images/gui_weather.png

[fileView]: ./images/file_view.png
[apiKey]: ./images/api_key.png
