# Critical TechWorks Challenge

My Setup  
• Done on a bottom navigation views activity template  
• Used Android Studio Jellyfish version  
• Simulated on Pixel 8 Pro API 33

Introduction  
You will build a smartphone app that should be able to show top headlines for a specific source (BBC news)
https://newsapi.org/docs/endpoints/top-headlines

Design  
You don’t need to waste too much time in doing complex designs, but you should take into account several screen dimensions and orientations.

Constraints  
• The app must be written in Kotlin or Java, preferably Kotlin.  
• The app must run on the latest released Android version/API level.  
• The app must be built using the latest major Android Studio version,without requiring the reviewer to modify any existing code.  
• The app must support both portrait and landscape modes without crashing at any time.  
• The app may use libraries for HTTP/REST or JSON, such as Retrofit2+, Moshi, Gson, etc...  
• The app must include unit tests  

Story 1: When the user launches the application, he should land in a screen where is possible to see top headlines for the specific news source  
Acceptance criteria:
1. News provider name should be showed as a screen title 2. Headlines are presented in a list format.
2. Each cell should present the headline title
3. Headlines must be sorted by date
4. The user must be able to scroll through the list of headlines
5. Each cell should present headline image, if available (download and cache it, don’t bundle it)

Story 2: When the user taps on a headline, he should be taken to a new screen  
Acceptance criteria:
1. Tapping on a headline presents a new screen.
2. Image, title, description and content should be displayed, if available

Bonus Story 3: When user opens the application, it should ask for a fingerprint identification, if available  
Acceptance criteria:
1. If the device has a fingerprint scanner and it’s configured in the device, user should be required to use it when he opens the application
2. If the device doesn't have fingerprint scanner or it’s not configured, then it should open normally

Bonus Story 4: A new flavor should be created to present news for another source  
Acceptance criteria:
1. User should land in a different news source if running another target
2. Headlines should be presented according to the target that was selected



![image](https://github.com/franciscovidalsantos/CriticalTechWorksChallenge/assets/65774935/65babba4-143f-4b04-b2b6-b91df94b2442)
