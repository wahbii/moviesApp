# MoviesAPP 
build using Clean Arch with mvvm ;

Clean Architecture is a software design approach that separates the software system into distinct layers, each with a specific purpose and responsibility. 
The main goal of Clean Architecture is to enable independent development, testing, and deployment of each layer, resulting in a system that is flexible and maintainable.

The key layers are:

  - Presentation Layer: Responsible for displaying the data to the user and handling user interactions. In Android, this is typically implemented using Activities, Fragments, and ViewModels.

  - Domain Layer: Contains the business logic of the application. This layer is platform-independent and contains use cases or interactors.

  - Data Layer: Responsible for data retrieval, whether from a remote source (web service) or a local source (database). This layer includes repositories that abstract the data sources.



In this implementation, we use Flow and LiveData to observe and exchange data with the views.
Retrofit is used for consuming web services, and Hilt is used for dependency injection (DI).






