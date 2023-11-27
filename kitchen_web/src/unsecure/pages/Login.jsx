import { Button, FormControl, FormLabel, Grid, InputAdornment, Stack, TextField } from "@mui/material";
import { registerChecker } from "../../logic/registerLogic"
import { useState } from "react";
import { Visibility, VisibilityOff } from "@mui/icons-material";
import { request } from "../../fetch/fetch";
import { useNavigate } from "react-router-dom";
import { setAuthToken, setUserLogin } from "../../storage/localStorage";

export default function Login() {
    const [showPassword, setShowPassword] = useState(false);
    const [password, setPassword] = useState(null);
    const [login, setLogin] = useState(null);
    const navigate = useNavigate();

    function onLogin(e) {
        e.preventDefault();

        if (registerChecker(password, login)) {
            request("POST", "/api/auth/kitchen/login", {
                login: login,
                password: password,
            })
                .then((response) => {
                    setUserLogin(response.data.login)
                    setAuthToken(response.data.token);
                    navigate("/kitchen/orders")

                }).catch((error) => {
                    navigate("/kitchen")
                });
        }

    }

    return <Grid
        container
        spacing={0}
        direction="column"
        alignItems="center"
        justifyContent="center"
        sx={{ minHeight: '85vh' }}
    >
        <FormControl >
            <FormLabel></FormLabel>
            <Stack spacing={2}>
                <TextField
                    label="Login"
                    required
                    onChange={(e) => setLogin(e.target.value)}
                >
                </TextField>
                <TextField
                    onChange={(e) => setPassword(e.target.value)}
                    required
                    label="Password"
                    type={showPassword ? "text" : "password"}
                    helperText="your password and login was generated, don't share it"
                    InputProps={{
                        endAdornment: (
                            <InputAdornment
                                position="end"
                                onClick={(e) => showPassword ? setShowPassword(false) : setShowPassword(true)}
                            >
                                {showPassword ? <Visibility /> : <VisibilityOff />}
                            </InputAdornment>
                        ),
                    }}
                />
            </Stack>
            <Button
                type='submit'
                size="large"
                variant="contained"
                sx={{ backgroundColor: '#798777' }}
                onClick={onLogin}
            >Login</Button>
        </FormControl>
    </Grid>

}