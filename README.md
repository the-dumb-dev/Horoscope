
FR:
Si vous voulez seulement l'application allez à :https://github.com/the-dumb-dev/apk-Only

Mon application Android va automatiquement chercher votre horoscope. Pour cela, vous devez entrer votre signe astrologique, mais pas de panique : ces données restent stockées sur votre téléphone.

L’application demande l’autorisation d’accéder complètement au réseau, mais j’utilise cette permission uniquement pour effectuer la recherche sur Internet. Si vous avez des doutes à ce sujet, je vous invite à consulter le fichier NetworkManager.kt de mon dépôt. Celui-ci est commenté afin que n’importe qui puisse comprendre son fonctionnement.

Une fois votre horoscope récupéré, l’application l’affiche dans les plus brefs délais — en quelques secondes tout au plus. Cependant, la durée d’affichage dépend également de votre connexion Internet.

Pour toute question, remarque ou idée de mise à jour, vous pouvez me contacter à l’adresse suivante : the-dumb-dev@protonmail.com

Pas de spam ni de messages indésirables, s’il vous plaît :-)

pour construire vous même l'application, vous devez installer le sdk android, puis copier tout le github sur votre machine puis placez vous dans le 

dossier juste avant app/ puis lancer la commande ./gradlew clean assembleDebug sur dans votre terminal, apres cela le .apk se trouveras dans app/build/outputs/apk/debug/



EN:
If you only want the application, go to:
https://github.com/the-dumb-dev/apk-Only

My Android application automatically retrieves your horoscope. To do so, you must enter your zodiac sign, but don’t worry: this data remains stored on your phone.

The application requests permission to access the network, but I use this permission only to perform the search on the Internet. If you have any doubts about this, I invite you to check the NetworkManager.kt file in my repository. It is commented so that anyone can understand how it works.

Once your horoscope has been retrieved, the application displays it as quickly as possible—within a few seconds at most. However, the loading time also depends on your Internet connection.

For any questions, comments, or ideas for future updates, you can contact me at:
the-dumb-dev@protonmail.com

No spam or unsolicited messages, please :-)

To build the application yourself, you must install the Android SDK. Then, clone or copy the entire GitHub repository to your computer and navigate to the project’s root directory, the folder containing app/. Finally, run the following command in your terminal:
bash

./gradlew clean assembleDebug

Once the build is complete, the .apk file will be located at:
app/build/outputs/apk/debug/
