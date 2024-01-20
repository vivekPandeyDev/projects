export function isLoggedIn() {
	const localData = JSON.parse(
		localStorage.getItem("sb-sfyqbcajmcyaqdpprkuv-auth-token") || "{}"
	);
	return !!localData?.access_token;
}
