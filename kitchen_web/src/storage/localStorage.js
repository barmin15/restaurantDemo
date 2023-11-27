export const getAuthToken = () => {
    return window.sessionStorage.getItem("auth_token");
  };
  
  export const setAuthToken = (token) => {
    window.sessionStorage.setItem("auth_token", token);
  };
  
  export const setUserLogin = (login) => {
    window.sessionStorage.setItem("login", login);
  }
  
  export const getUserLogin = () => {
   return window.sessionStorage.getItem("login");
    
  }