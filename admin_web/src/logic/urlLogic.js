export function getFoodCategory(url) {
    let result = [];
    let i = url.lastIndexOf("/") - 1;

    for (i; i > 0; i--) {
      if (url[i] === "/") {
        return result.join("");
      } else {
        result.unshift(url[i]);
      }
    }
  }

  export function getProductId(url) {
    let result = "";
    let i = url.lastIndexOf("/") + 1;

    for (i; i < url.length; i++) {
      result += url[i];
    }
    return result;
  }