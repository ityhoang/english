# Base Project
    🚀 Created by 𓆩♕𓆪ityhoang𓆩♕𓆪 developer 🎉
# Android
    - Kotlin
    - Presentation impl with MVVM
    - Pipline verify code PR
    - Unit test > 90%

# Technical architecture components
- MVVM
- Code analysis: ktlint + detekt
- State management: livedata
- Dependency injection: hilt
- Database: room
- Network: okHttp + retrofit
- Independent processes: coroutines + workManager
- l10n: resources + strings.xml
- Unit testing: mockito + JUnit

# Architecture

Apply MVVM (with ViewModel used to replace the traditional ViewModel).

```
|-----------------  Layers  ------------------|
| Presentations  |  Doamin     |   Data Layer |
|:-------------------------------------------:|

|--------------------------  Actual  ---------------------------|
| Presentations  |        Doamin          |         Data        |
|:-------------------------------------------------------------:|
|  UI <--> ViewModel <--> UseCase <--> Repository <--> API/Local|
|:-------------------------------------------------------------:|
|:----       Entity         ----|----       Model      --------:|
|:-------------------------------------------------------------:|
```

