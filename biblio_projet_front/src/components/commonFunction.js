export function formatDate(sDate) {
  //TODO : controle validité date et controle que c'est bien une String
  return (
    sDate.substr(8, 2) + "/" + sDate.substr(5, 2) + "/" + sDate.substr(0, 4)
  );
}
