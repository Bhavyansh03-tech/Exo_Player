# ExoPlayer Example

This repository demonstrates how to use ExoPlayer in a Jetpack Compose application. It includes three different player implementations:
- **Online Player**: Streams video from an online URL.
- **Raw Player**: Plays a video stored in the raw resources of the app.
- **YouTube Player**: Embeds a YouTube video using a `WebView`.

## Prerequisites

- Android Studio with Jetpack Compose setup
- ExoPlayer dependency in your `build.gradle` file
- Internet permissions if using the Online Player

1. Clone the repository:

   ```sh
     git clone https://github.com/Bhavyansh03-tech/Exo_Player.git
   ```
   
2. Open the project in Android Studio.
3. Build the project and run it on an emulator or a physical device.

## Screenshots
<img src="https://github.com/user-attachments/assets/7b16e0de-536a-42d9-862b-342ba2fcd667" alt="First Screenshot" style="width: 200px; height: auto; margin-right: 10px;">
<img src="https://github.com/user-attachments/assets/60666de7-e93a-4f88-934d-d4bb0d89e22e" alt="Second Screenshot" style="width: 200px; height: auto; margin-right: 10px;">
<img src="https://github.com/user-attachments/assets/cd82e250-c62f-4335-965f-cf3e48ab2b0e" alt="Third Screenshot" style="width: 200px; height: auto;">

## Setup

1. **Add Dependencies**

   Make sure you have the following dependencies in your `build.gradle` file:

   ```toml
   [libraries]
   # YOUTUBE PLAYER :->
   androidx-webkit = { module = "androidx.webkit:webkit", version.ref = "webkit" }
   
   # MEDIA EXO PLAYER :->
    androidx-media3-exoplayer = { module = "androidx.media3:media3-exoplayer", version.ref = "media3Exoplayer" }
    androidx-media3-exoplayer-hls = { module = "androidx.media3:media3-exoplayer-hls", version.ref = "media3Exoplayer" }
    androidx-media3-ui = { module = "androidx.media3:media3-ui", version.ref = "media3Exoplayer" }
   ```

2. **Add Internet Permission (for Online Player)**

   In your `AndroidManifest.xml`, add the following permission:

   ```xml
   <uses-permission android:name="android.permission.INTERNET"/>
   ```

## Composables

###  OnlinePlayer

Streams video from an online URL.

```kotlin
// Media Item :->
val mediaItem = MediaItem.fromUri("https://cdn.pixabay.com/video/2024/04/18/208442_large.mp4") // Apply Changes.

// Media source :->
val mediaSource: MediaSource = ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory()) // Add this.
    .createMediaSource(mediaItem)

val exoPlayer = remember {
    ExoPlayer.Builder(context).build().apply {
        setMediaSource(mediaSource)  // Apply Changes.
        prepare()
        playWhenReady = true
    }
}
```

### RawPlayer

Plays a video stored in the raw resources of the app.

```kotlin
@Composable
fun RawPlayer() {

    // Lifecycle :->
    var lifecycle by remember {
        mutableStateOf(Lifecycle.Event.ON_CREATE)
    }

    // Get context :->
    val context = LocalContext.current

    // Media Item :->
    val mediaItem = MediaItem.fromUri("android.resource://${context.packageName}/${R.raw.sample}")

    // Media source :->
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            setMediaItem(mediaItem)
            prepare()
            playWhenReady = true
        }
    }

    // Lifecycle for composable :->
    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            lifecycle = event
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            exoPlayer.release()
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    // Android View :->
    AndroidView(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f),
        factory = {
            PlayerView(context).also { playerView ->
                playerView.player = exoPlayer
            }
        },
        update = {
            when (lifecycle){
                Lifecycle.Event.ON_RESUME -> {
                    it.onPause()
                    it.player?.pause()
                }
                Lifecycle.Event.ON_PAUSE -> {
                    it.onResume()
                }
                else -> Unit
            }
        }
    )
}
```

### YoutubePlayer

Embeds a YouTube video using a `WebView`.

```kotlin
@Composable
fun YoutubePlayer(videoId: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize().padding(top = 25.dp),
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.javaScriptEnabled = true
                loadUrl("https://youtu.be/$videoId")
            }
        }
    )
}
```

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request for any improvements or bug fixes.

1. Fork the repository.
2. Create your feature branch (`git checkout -b feature/your-feature`).
3. Commit your changes (`git commit -am 'Add some feature'`).
4. Push to the branch (`git push origin feature/your-feature`).
5. Create a new Pull Request.

## Contact

For questions or feedback, please contact [@Bhavyansh03-tech](https://github.com/Bhavyansh03-tech) on GitHub or connect with me on [LinkedIn](https://www.linkedin.com/in/bhavyansh03/).

---
