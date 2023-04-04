# SneakerApp

## Videos and ScreenShots

### video links <br>

[flow_screen_recording](https://drive.google.com/file/d/1ZpQBASaoGQE7H43Bsfer41AEdr2hu1sC/view?usp=share_link) <br>
[Detail_page_video](https://drive.google.com/file/d/1cJ6DjpEVfwL1mCZmz2_8WTp2ou0sH2Bg/view?usp=share_link) <br>
[Home_screen_searching](https://drive.google.com/file/d/1PjzXNTgs0USk8tAvDP6XV5qAYZKnB1np/view?usp=share_link) <br>

### ScreenShots <br>

[cart_screen](https://drive.google.com/file/d/1ktTLihOT2gf0xvarO0obDPez9PYA_BXj/view?usp=share_link) <br>
[detail_screen](https://drive.google.com/file/d/1YFKA_z5ZlHnR4PzGYUp0M7rFq6hemfcP/view?usp=share_link) <br>
[home_screen](https://drive.google.com/file/d/145ZNpphq2AdfFXxfRaddc30ANkxIJtaH/view?usp=share_link) <br>

### All ScreenShots and Videos <br>
[All](https://drive.google.com/drive/folders/15f7a_DybQyYElQbOxKjq6SLeyshVKNos?usp=share_link)


## About

- Sneaker app that follows the MVVM architecture and utilizes the single activity pattern.
- The app utilizes Hilt dependency injection and Navigation Graph to navigate between fragments.
- Room Database is used to save items in the cart.
- A common toolbar has also been implemented for all screens through the use of the include tag.
- Used Glide library for Image Loading.
- Used ViewPager2 for Carousel implementation.
- Used Espresso and Mockito framework for testing


## Features
- Displays a grid (column size of 2) of available sneakers. The items contains the image, price and name of the sneaker.
- Home screen contains Searching functionality by brand name.
- The sneaker details page contains the selected sneakers title, name, image, brand and price.
- “Add to Cart” button adds the sneaker to a checkout cart page.
- The checkout page contains all the sneakers added in a list displaying their image and price.
- The checkout page also shows the total price of all the sneakers in the end.
- Items are removable from the cart.
- Added UI tests for Fragments.
- Added Test for Room and viewModel.

## References
 
- [Hilt dependency injection](https://developer.android.com/training/dependency-injection/hilt-android)
- [Hilt dependency injection](https://www.youtube.com/watch?v=64hHvlR-d4k&list=PLRKyZvuMYSIOSigPsU9_tbO0uDyaZ8Ycf)
- [Room DB with Hilt](https://svvashishtha.medium.com/using-room-with-hilt-cb57a1bc32f)