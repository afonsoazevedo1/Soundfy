# Soundfy

Soundfy é um aplicativo de streaming de música inspirado no Spotify. Ele utiliza a API pública do Deezer para buscar artistas, álbuns e músicas. Desenvolvido com Android nativo usando Kotlin, Jetpack Compose, e arquitetura MVVM com Hilt para injeção de dependência.

## Funcionalidades

-  Buscar e listar artistas populares
-  Visualizar álbuns de cada artista
-  Visualizar faixas de um álbum
-  Criar playlists personalizadas
-  Avatar persistente entre as telas principais

## Tecnologias Utilizadas

- **Linguagem:** Kotlin
- **UI:** Jetpack Compose
- **Arquitetura:** MVVM
- **Injeção de Dependência:** Hilt
- **Consumo de API:** Retrofit + Gson
- **Persistência local:** SharedPreferences (Futuramente com Room ou DataStore)
- **Testes:** JUnit, MockWebServer, kotlinx-coroutines-test
