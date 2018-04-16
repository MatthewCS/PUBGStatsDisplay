# PUBGStatsDisplay

[Check out our project on devpost!](https://devpost.com/software/pubg-stats-display)

Our program is a Java/Swing application allowing players to choose their four favorite items from the game, along with the ability to input their own PUBG username to view their most recent stats from the past 14 days. With this data, a custom image is generated, and is able to be saved locally as a .PNG file.

The stats are obtained via the PUBG API through our own custom-made class tailored to our needs We also used custom classes based on Swing for the different UI elements of our window.

This is a lightweight and easy-to-use tool for generating anaesthetically pleasing images, which can be sent to friends or posted on-line to show off your profile.

After this hackathon is over, our project will be posted onto GitHub for anyone to look at. We will also possibly make some slight changes post-hackathon, such as more customization or more user-friendly error pop-up messages.

* **NOTES:**
  + Made for Hack Upstate XI
  + You must grab your own API key via https://developer.playbattlegrounds.com/#, then use your key so that it is the only text in APIKEY.txt
  + Assets grabbed from https://github.com/pubg/api-assets
  + Error pop-ups were hastily developed. The error is most likely a 404 (player not found) or a 429 (too many requests, slow down) with a NullPointerException
  + Implements https://github.com/rkalla/imgscalr and https://github.com/fangyidong/json-simple

# CREDITS

* **Created by:**
  + https://github.com/MatthewCS (GUI)
  + https://github.com/MikeAlexander1 (Presentation)
  + https://github.com/Limey12 (API)
  + https://github.com/kkzero2016 (API)

* **Special thanks to:**
  + PUBG Madglory for much needed and appreciated personal help with this project
  + https://github.com/ominousmango and https://github.com/aloyark for sharing with us a great work area at Hack Upstate XI
  + https://github.com/1egoman for help with Git
