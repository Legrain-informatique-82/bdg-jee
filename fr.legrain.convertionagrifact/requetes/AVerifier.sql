SELECT [nEntreprise]
      ,[Nom]
      ,[TypeClient]
      ,[CibleCommercial]
      ,[Observations]
      ,[FinMois]
      ,[Remise]
      ,[TTVA]
      ,[FacturationTTC]
      ,[NCompteC]
      ,[Civilite]
  FROM [BERTRINE].[dbo].[Entreprise]
where [NomLivraison] is not null