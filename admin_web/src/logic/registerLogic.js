import validator from 'validator'

export const passwordChecker = (password, repeatPassword) => {
    return password === repeatPassword;
}

export const registerChecker = (password, repeatPassword, login) => {
    return [password, repeatPassword, login].filter(e => e === null).length === 0;
}

export const emailValidator = (email) => {
    if (validator.isEmail(email)) {
        return true;
    }
        return true;
}