# Countries App

The Countries App is a simple Android application that fetches a list of countries from a remote JSON source and displays them in a RecyclerView. The project demonstrates modern Android development best practices using Kotlin, Clean Architecture, and MVVM pattern.

---

## About

This app showcases a list of countries with details like name, region, code, and capital. Each item is displayed in a well-structured format inside a RecyclerView.

The main goal of the app is to demonstrate:

- Clean and maintainable architecture
- Separation of concerns with Data, Domain, and Presentation layers
- Proper use of ViewModel, LiveData, and Retrofit
- Kotlin Coroutines for asynchronous data loading

---

## Features

- Fetch countries from a remote JSON API
- Display list of countries using RecyclerView
- Show name, region, capital, and code in a structured format
- Follow MVVM architecture
- Device rotation support

---

## ScreenShots
|<img alt="screenshot" height="600" src=https://github.com/Gdeepika56/Walmart-Deepika-CodingAssessment/blob/main/country_list.png width="280"/>|<video src=https://github.com/Gdeepika56/Walmart-Deepika-CodingAssessment/blob/main/country.mp4/>|
---

## API Used

The data is fetched from the following public JSON:

[https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json)

---

## Package Structure
```text
com.example.countrieswalmart
│
├── data
│   ├── entities          # API response models
│   ├── commons           # Sealed ApiResponse class, Constants
│   ├── mappers           # Entity to domain model converters
│   ├── remote            # Retrofit configuration and service
│   └── repository        # Data-layer logic implementation
│
├── domain
│   ├── data              # Domain-level models
│   ├── repository        # Interfaces for repositories
│   └── usecase           # Business logic
│
├── presentation
│   ├── view              # UI components (MainActivity, Adapter)
│   └── viewModel         # ViewModel and Factory

