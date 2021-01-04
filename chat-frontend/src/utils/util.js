export function checkUser(userToCheck) {
  if (userToCheck === null || userToCheck === undefined) {
    return null;
  }

  const { email, id, password, username } = userToCheck;

  if (id === null || id === undefined) {
    return null;
  }

  if (
    username === null ||
    username === undefined ||
    email === null ||
    email === undefined ||
    password === null ||
    password === undefined
  ) {
    return null;
  }

  return true;
}

export function messageBasedOnReturnValue(status, view) {

  if(view === "login")
    return messageForLogin(status);
  
  if(view === "register")
    return messageForRegistry(status);

}

export function messageForLogin(status) {
  switch (status) {
    case 200:
      return {message: 'User wird eingeloggt ...', status};
    case 400:
      return 'User konnte nicht eingeloggt werden!';
    case 500:
        return 'Fehler sind geschehen!';
    default:
      return null;
  }
}

export function messageForRegistry(status) {
  switch (status) {
    case 200:
      return 'User wurde erstellt';
    case 400:
      return 'User konnte nicht eingeloggt werden!';
    default:
      return null;
  }
}
