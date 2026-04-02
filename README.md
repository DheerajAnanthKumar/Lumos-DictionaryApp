# LUMOS — Your Digital Lantern for Language

**Illuminate your vocabulary with a minimalist, high-performance dictionary experience.**

[![Platform: Android](https://img.shields.io/badge/Platform-Android-3DDC84?style=flat-square&logo=android&logoColor=white)](https://developer.android.com/studio)
[![Build: Kotlin](https://img.shields.io/badge/Build-Kotlin-7F52FF?style=flat-square&logo=kotlin&logoColor=white)](https://kotlinlang.org/)
[![UI: Jetpack Compose](https://img.shields.io/badge/UI-Jetpack_Compose-4285F4?style=flat-square&logo=jetpackcompose&logoColor=white)](https://developer.android.com/jetpack/compose)

---

## 📖 Overview

**LUMOS** (Latin for "light") is a sophisticated, minimalist dictionary application engineered for speed and clarity. In a world of cluttered interfaces, LUMOS strips away the noise to provide a focused environment where language takes center stage. 

Designed with **Jetpack Compose** and a focus on **asynchronous data fetching**, LUMOS delivers instant definitions, synonyms, and contextual examples with zero friction.

<img width="350" alt="image" src="https://github.com/user-attachments/assets/87bbae47-3ad2-4f43-9977-86902f9a7fba" />


## ✨ Key Features

* **⚡ Instant Illumination:** High-speed word lookups powered by the Free Dictionary API.
* **📚 Deep Context:** Go beyond definitions with phonetic transcriptions, synonyms, and antonyms.
* **✍️ Practical Usage:** Real-world example sentences to help you master word application.
* **📱 Modern UI:** A clean, "light-first" aesthetic utilizing Material 3 components and fluid animations.
* **🛠 Fault-Tolerant:** Robust error handling for "word not found" scenarios and network interruptions.

## 🛠 Tech Stack

LUMOS is built using the industry's most modern Android development standards:

* **Language:** [Kotlin](https://kotlinlang.org/) — Utilizing coroutines for non-blocking UI operations.
* **UI Framework:** [Jetpack Compose](https://developer.android.com/jetpack/compose) — Declarative UI for a reactive and modern user experience.
* **Networking:** [Retrofit](https://square.github.io/retrofit/) — Type-safe REST client for efficient API communication.
* **Architecture:** Follows clean architecture principles for scalability and maintainability.
* **Design:** [Material Icons](https://fonts.google.com/icons) & Material 3 Design System.

---
## 📸 Visual Walkthrough

Experience the fluid, "light-first" interface of LUMOS.

| **1. Searching State** | **2. Core Definition** |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/3aa4e7fe-9335-4fa7-a0e0-8a8d77fc4b89"  width="350" alt="LUMOS Search Loading"> | <img src="https://github.com/user-attachments/assets/6af87c06-c54f-408d-8b87-6b1f82fab8b0"  width="350" alt="LUMOS Definition View"> |
| **Instant Feedback:** A clean, animated loading state powered by Retrofit ensures the user knows their request is being processed. | **Linguistic Clarity:** Definitions are presented in high-contrast cards featuring phonetic transcriptions and parts of speech. |

| **3. Extended Context** | **4. Error Handling** |
|:---:|:---:|
| <img src="https://github.com/user-attachments/assets/afd8057a-e749-4f29-9a09-93b86fed568a" width="350" alt="LUMOS Synonyms and Examples"> | <img src="https://github.com/user-attachments/assets/cfdf7e6e-b63a-4700-9203-35da27706a5a" width="350" alt="LUMOS Error State"> |
| **Deep Insight:** Beyond basic meanings, LUMOS illuminates language with synonyms, antonyms, and real-world usage examples. | **Graceful Recovery:** Built with fault-tolerance to clearly notify users of spelling errors or missing data. |

---
## 🚀 Getting Started

### Prerequisites
* **Android Studio** (Hedgehog or newer recommended)
* **JDK 17** or higher
* An Android device or emulator running **API 26 (Oreo)** or above

### Installation

1.  **Clone the repository:**
    ```bash
    git clone [https://github.com/yourusername/Lumos-DictionaryApp.git](https://github.com/yourusername/Lumos-DictionaryApp.git)
    ```
2.  **Open Project:**
    Launch Android Studio and select `Open` -> Navigate to the cloned `LUMOS` folder.
3.  **Sync Gradle:**
    Wait for the project to sync and download all necessary dependencies.
4.  **Run:**
    Connect your device and click the **Run** button (Shift + F10).

## 📡 API Reference

This project leverages the [Free Dictionary API](https://dictionaryapi.dev/) to provide high-quality linguistic data without the need for authentication keys, ensuring a seamless setup for developers.

---
## ✍️ About the Author

Created by **Dheeraj Ananth Kumar** — fitness tech enthusiast and full-stack developer.
Check out more of my work on [GitHub](https://github.com/DheerajAnanthKumar).
