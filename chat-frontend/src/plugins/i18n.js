import Vue from 'vue';
import VueI18n from 'vue-i18n';

Vue.use(VueI18n);

/**
 * 
 */
export default new VueI18n({
  locale: 'de-DE', //default value
  messages: {
    'en-US': {
      loginView: {
        title: 'Sign in',
        user: {
          username: 'Username',
          password: 'Password',
        },
        createAccount: 'Create account',
      },
      registrieView: {
        title: 'Registration',
        user: {
          username: 'Username',
          email: 'Email',
          password: 'Password',
          repeatPassword: 'Confirm',
        },
        signIn: 'Sign in instead',
      },
      chatView: {
        user: {
          editValue: {
            title: 'Change:',
            createAvatar: 'Create your avatar here',
            changePassword: 'Change password',
            deleteAccount: 'Close Account',
            reallyDeleteAccount: 'Do you really want to delete the account ?',
            lastQuestionBeforeDeleteAccount: 'Are you sure?',
            deleteInfo: 'Account could be deleted successfully',
            error: {
              404: 'Kontodaten wurden nicht gefunden',
              202: 'Änderungen wurden erfolgreich übernommen',
            },
          },
          userDetails: {
            tooltip: {
              email: 'EMail',
              id: 'ID from Account',
              username: 'Nickname',
              avatarName: 'Avatar Name',
            },
            action: {
              avatarPicture: 'Add an avatar picture',
            },
          },
          themeSwitcher: {
            title: 'Change the theme',
            light: 'LIGHT',
            dark: 'DARK',
          },
        },
        settings: {
          title: 'All about my account',
          languagesSwitcher: {
            title: 'Change the language',
          },
        },
        navigationbar: {
          translate: {
            de: 'German',
            en: 'English',
          },
          settings: 'User settings',
          notification: {
            game: 'A game is available',
            message: 'A new message has arrived',
          },
        },
        games: {
          title: 'Competition',
          winWheel: 'Win-Wheel',
        },
        sidebar: {
          sidebarLeft: {
            sidebarMini: {
              groupDialog: {
                title: 'Create a new group',
                newTitle: 'Title of group',
                addUsers: 'Add Users to group',
              },
              groupInfo: {
                subTitle: 'Group',
                groupAdmin: 'Admin',
              },
            },
            admin: {
              addUserToGroup: 'Group members',
            },
          },
        },
      },
      aboutView: {},
      errors: {
        loginView: {
          "200": "Alles ok",
          "400": "Unfortunately, the login data of the user is not correct.",
          "404": "The specified user was not found",
        },
        registryView:{
          "200": "User could be created. You will be logged in.",
          "400": "Die Daten stimmen nicht",
          "409": "This user already exists. Please choose another email and username."
        },
        "500": "Critical server errors have occurred. If this occurs again, contact the administrator."
      },
      actions: {
        close: 'Close',
        save: 'Save',
        login: 'Sign In',
        create: 'Create',
        change: 'Change',
        abort: 'Abort',
      },
    },
    'de-DE': {
      loginView: {
        title: 'Anmeldung',
        user: {
          username: 'Username',
          password: 'Passwort',
        },
        createAccount: 'Konto erstellen',
      },
      registrieView: {
        title: 'Konto erstellen',
        user: {
          username: 'Username',
          email: 'Email',
          password: 'Passwort',
          repeatPassword: 'Wiederholen',
        },
        signIn: 'Stattdessen Anmelden',
      },
      chatView: {
        user: {
          editValue: {
            title: 'Ändere:',
            createAvatar: 'Erstelle dein avatar hier',
            changePassword: 'Password ändern',
            deleteAccount: 'Konto schließen',
            reallyDeleteAccount: 'Wollen Sie wirklich das Konto löschen ?',
            lastQuestionBeforeDeleteAccount: 'Bist Du dir ganz Sicher?',
            deleteInfo: 'Konto wurde erfolgreich geloescht',
            error: {
              404: 'Kontodaten wurden nicht gefunden',
              202: 'Änderungen wurden erfolgreich übernommen',
            },
          },
          userDetails: {
            tooltip: {
              email: 'EMail',
              id: 'ID deines Kontos',
              username: 'Nickname',
              avatarName: 'Avatar Name',
            },
            action: {
              avatarPicture: 'Füge ein Avatar hinzu',
            },
          },
          themeSwitcher: {
            title: 'Ändere das theme',
            light: 'HELL',
            dark: 'DUNKEL',
          },
        },
        settings: {
          title: 'Alles über mein Konto',
          languagesSwitcher: {
            title: 'Ändere die Sprache',
          },
        },
        navigationbar: {
          translate: {
            de: 'Deutsch',
            en: 'Englisch',
          },
          settings: 'Benutzer Einstellungen',
          notification: {
            game: 'Ein Spiel ist verfügbar',
            message: 'Eine neu Nachricht ist eingetroffen',
          },
        },
        games: {
          title: 'Gewinnspiel',
          winWheel: 'Glücksrad',
        },
      },
      aboutView: {},
      errors: {
        loginView: {
          "200": "Alles ok",
          "400": "Die login daten des Benutzer stimmen leider nicht.",
          "404": "Der angegebene Benutzer wurde nicht gefunden",
        },
        registryView:{
          "200": "User konnte erstellt werden. Sie werden eingeloggt.",
          "400": "Die Daten stimmen nicht",
          "409": "Dieser Benutzer ist bereits vorhanden. Bitte waehlen Sie eine andere Email und Usernamen aus."
        },
        "500": "Kritische Server Fehler sind aufgetreten. Falls dies erneut auftritt melden Sie sich beim Administrator."
      },
      actions: {
        close: 'Schließen',
        save: 'Speichern',
        login: 'Anmelden',
        create: 'Erstellen',
        change: 'Verändern',
        abort: 'Abbruch',
      },
    },
  },
});
